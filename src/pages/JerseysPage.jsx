import { useEffect, useState } from "react";
import api from "../api/http";
import Modal from "../components/Modal.jsx";
import JerseyForm from "../components/JerseyForm.jsx";

const PAGE_SIZE = 5;

export default function JerseysPage(){
  const [q,setQ] = useState("");
  const [page,setPage] = useState(0);
  const [rows,setRows] = useState([]);
  const [meta,setMeta] = useState({page:0,totalPages:1});
  const [open,setOpen] = useState(false);
  const [editing,setEditing] = useState(null);
  const [form,setForm] = useState({});

  useEffect(()=>{ load(); },[q,page]);

  async function load(){
    const res = await api.get("/jerseys",{ params:{ q, page, size: PAGE_SIZE } });
    const data = res.data;
    setRows(data.content ?? data);
    if (data.content){
      setMeta({ page: data.number, totalPages: data.totalPages });
    } else {
      // simple fallback
      setMeta({ page, totalPages: 1 });
    }
  }

  function openAdd(){ setEditing(null); setForm({ inStock:true, size:"M" }); setOpen(true); }
  function openEdit(row){ setEditing(row); setForm(row); setOpen(true); }

  async function save(){
    if (editing) await api.put(`/jerseys/${editing.id}`, form);
    else await api.post("/jerseys", form);
    setOpen(false); await load();
  }
  async function remove(id){
    if (!confirm("Delete this jersey?")) return;
    await api.delete(`/jerseys/${id}`); await load();
  }

  return (
    <>
      <div className="page-head">
        <h1 className="h1">Jerseys</h1>
        <div className="toolbar">
          <input type="search" placeholder="Search…" value={q}
                 onChange={e=>{setPage(0);setQ(e.target.value);}} />
          <button className="btn-primary" onClick={openAdd}>Add</button>
        </div>
      </div>

      <div className="card">
        <div className="jersey-grid">

  {rows.map(j => (
    <div className="jersey-card" key={j.id}>
      
      <img
        className="jersey-image"
        src={j.imageLink || j.image_link || `https://picsum.photos/seed/jersey-${j.id}/200/200`}
        alt={j.name}
      />

      <div className="jersey-info">
        <h3>{j.name}</h3>
        <p>{j.club}</p>
        <p>Size: {j.size}</p>
        <p>Price: ${Number(j.price).toFixed(2)}</p>

        <span className={`stock-badge ${j.inStock ? "in" : "out"}`}>
          {j.inStock ? "In stock" : "Out"}
        </span>
      </div>

      <div className="jersey-actions">
        <button onClick={() => openEdit(j)}>Edit</button>
        <button className="btn-danger" onClick={() => remove(j.id)}>Delete</button>
      </div>

    </div>
  ))}

  {rows.length === 0 && (
    <p style={{ color: "var(--muted)", padding: 16 }}>No items</p>
  )}
  

</div>


        <div className="pagination">
  <button onClick={() => setPage(0)}>Back to Page 1</button>

  <button onClick={() => setPage(p => Math.max(0, p - 1))}>Prev</button>

  <span className="badge">Page {meta.page + 1}</span>

  <button onClick={() => setPage(p => Math.min(meta.totalPages - 1, p + 1))}>
    Next
  </button>
</div>

      </div>

      <Modal open={open} title={editing ? "Edit jersey" : "Add jersey"} onClose={()=>setOpen(false)} onSubmit={save} submitText="Save">
        <JerseyForm value={form} onChange={setForm} />
      </Modal>
    </>
  );
}
