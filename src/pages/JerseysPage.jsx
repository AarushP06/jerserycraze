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
        <table className="table">
          <thead>
            <tr>
              <th>Item</th>
              <th>Club</th>
              <th>Size</th>
              <th>Price</th>
              <th>Stock</th>
              <th style={{textAlign:"right"}}>Actions</th>
            </tr>
          </thead>
          <tbody>
            {rows.map(j=>(
              <tr key={j.id}>
                <td>
                  <div style={{display:"flex",alignItems:"center",gap:12}}>
                    <img
                      className="thumb"
                      src={ j.imageLink || j.image_link || `https://picsum.photos/seed/jersey-${j.id}/80/80` }
                      alt={j.name}
                    />
                    <div>
                      <div style={{fontWeight:600}}>{j.name}</div>
                      <div style={{color:"var(--muted)",fontSize:12}}>{j.type}</div>
                    </div>
                  </div>
                </td>
                <td>{j.club}</td>
                <td>{j.size}</td>
                <td>${Number(j.price).toFixed(2)}</td>
                <td>
                  <span className="badge" style={{borderColor: j.inStock ? "rgba(34,197,94,.35)" : "#3b1e24", color: j.inStock ? "#a7f3d0" : "#fecaca"}}>
                    {j.inStock ? "In stock" : "Out"}
                  </span>
                </td>
                <td style={{textAlign:"right"}}>
                  <button onClick={()=>openEdit(j)}>Edit</button>{" "}
                  <button className="btn-danger" onClick={()=>remove(j.id)}>Delete</button>
                </td>
              </tr>
            ))}
            {rows.length===0 && (
              <tr><td colSpan="6" style={{padding:16,color:"var(--muted)"}}>No items</td></tr>
            )}
          </tbody>
        </table>

        <div className="pagination">
          <button onClick={()=>setPage(p=>Math.max(0,p-1))}>Prev</button>
          <span className="badge">Page {meta.page+1}</span>
          <button onClick={()=>setPage(p=>Math.min(meta.totalPages-1, p+1))}>Next</button>
        </div>
      </div>

      <Modal open={open} title={editing ? "Edit jersey" : "Add jersey"} onClose={()=>setOpen(false)} onSubmit={save} submitText="Save">
        <JerseyForm value={form} onChange={setForm} />
      </Modal>
    </>
  );
}
