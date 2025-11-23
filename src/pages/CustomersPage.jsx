import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import api from "../api/http";
import Modal from "../components/Modal.jsx";
import CustomerForm from "../components/CustomerForm.jsx";

const PAGE_SIZE = 5;

export default function CustomersPage(){
  const [q,setQ] = useState("");
  const [page,setPage] = useState(0);
  const [rows,setRows] = useState([]);
  const [meta,setMeta] = useState({page:0,totalPages:1});
  const [open,setOpen] = useState(false);
  const [editing,setEditing] = useState(null);
  const [form,setForm] = useState({});

  useEffect(()=>{ load(); },[q,page]);

  async function load(){
    const res = await api.get("/customers",{ params:{ q, page, size: PAGE_SIZE } });
    const data = res.data;
    setRows(data.content ?? data);
    if (data.content) setMeta({ page: data.number, totalPages: data.totalPages });
    else setMeta({ page, totalPages: 1 });
  }

  function openAdd(){ setEditing(null); setForm({}); setOpen(true); }
  function openEdit(row){ setEditing(row); setForm(row); setOpen(true); }

  async function save(){
    if (editing) await api.put(`/customers/${editing.id}`, form);
    else await api.post("/customers", form);
    setOpen(false); await load();
  }
  async function remove(id){
    if (!confirm("Delete this customer?")) return;
    await api.delete(`/customers/${id}`); await load();
  }

  return (
    <>
      <div className="page-head">
        <h1 className="h1">Customers</h1>
        <div className="toolbar">
          <input type="search" placeholder="Search…" value={q}
                 onChange={e=>{setPage(0);setQ(e.target.value);}} />
          <button className="btn-primary" onClick={openAdd}>Add</button>
        </div>
      </div>

      <div className="card">
        <table className="table">
          <thead>
            <tr><th>Name</th><th>Email</th><th style={{textAlign:"right"}}>Actions</th></tr>
          </thead>
          <tbody>
            {rows.map(c=>(
              <tr key={c.id}>
                <td>
                  <Link to={`/customers/${c.id}`} style={{color:"inherit",textDecoration:"none"}}>
                    <div style={{fontWeight:600}}>{c.firstName} {c.lastName}</div>
                    <div style={{color:"var(--muted)",fontSize:12}}>ID {c.id}</div>
                  </Link>
                </td>
                <td>{c.email}</td>
                <td style={{textAlign:"right"}}>
                  <button onClick={()=>openEdit(c)}>Edit</button>{" "}
                  <button className="btn-danger" onClick={()=>remove(c.id)}>Delete</button>
                </td>
              </tr>
            ))}
            {rows.length===0 && (
              <tr><td colSpan="3" style={{padding:16,color:"var(--muted)"}}>No customers</td></tr>
            )}
          </tbody>
        </table>

        <div className="pagination">
          <button onClick={()=>setPage(p=>Math.max(0,p-1))}>Prev</button>
          <span className="badge">Page {meta.page+1}</span>
          <button onClick={()=>setPage(p=>Math.min(meta.totalPages-1, p+1))}>Next</button>
        </div>
      </div>

      <Modal open={open} title={editing ? "Edit customer" : "Add customer"} onClose={()=>setOpen(false)} onSubmit={save} submitText="Save">
        <CustomerForm value={form} onChange={setForm} />
      </Modal>
    </>
  );
}
