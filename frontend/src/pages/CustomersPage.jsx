import { useEffect, useState } from "react";
import api from "../api/http.js";
import Modal from "../components/Modal.jsx";
import CustomerForm from "../components/CustomerForm.jsx";

const PAGE_SIZE = 5;

export default function CustomersPage() {
  const [q, setQ] = useState("");
  const [page, setPage] = useState(0);
  const [rows, setRows] = useState([]);
  const [meta, setMeta] = useState({ page: 0, totalPages: 1 });
  const [open, setOpen] = useState(false);
  const [editing, setEditing] = useState(null);
  const [form, setForm] = useState({});

  useEffect(() => {
    load();
  }, [q, page]);

  async function load() {
    try {
      const res = await api.get("/customers", {
        params: { q, page, size: PAGE_SIZE }
      });

      const data = res.data;
      setRows(Array.isArray(data.content) ? data.content : []);

      setMeta({
        page: data.number ?? 0,
        totalPages: data.totalPages ?? 1
      });
    } catch (err) {
      console.error("LOAD ERROR:", err);
    }
  }

  function openAdd() {
    setEditing(null);
    setForm({ firstName: "", lastName: "", email: "", phone: "" });
    setOpen(true);
  }

  function openEdit(row) {
    setEditing(row);
    setForm(row);
    setOpen(true);
  }

  async function save() {
    try {
      if (editing) await api.put(`/customers/${editing.id}`, form);
      else await api.post("/customers", form);
    } catch (err) {
      console.error("SAVE ERROR:", err);
    }
    setOpen(false);
    load();
  }

  async function remove(id) {
    if (!confirm("Delete this customer?")) return;
    await api.delete(`/customers/${id}`);
    load();
  }

  return (
    <>
      <div className="page-head">
        <h1 className="h1">Customers</h1>

        <div className="toolbar">
          <input
            type="search"
            placeholder="Search…"
            value={q}
            onChange={(e) => {
              setPage(0);
              setQ(e.target.value);
            }}
          />
          <button className="btn-primary" onClick={openAdd}>Add</button>
        </div>
      </div>

      <div className="card-grid">
        {rows.map(c => (
          <div className="customer-card" key={c.id}>
            <img
              className="card-img"
              src={`https://ui-avatars.com/api/?name=${encodeURIComponent(
                `${c.firstName} ${c.lastName}`
              )}&background=1f2937&color=fff&size=256`}
              alt="avatar"
            />

            <h3 className="card-title">{c.firstName} {c.lastName}</h3>
<div className="divider"></div>
<p className="card-sub">{c.email}</p>
<p className="card-sub">{c.phone || "No phone"}</p>

            <div className="card-buttons">
              <button onClick={() => openEdit(c)}>Edit</button>
              <button className="btn-danger" onClick={() => remove(c.id)}>
                Delete
              </button>
            </div>
          </div>
        ))}

        {rows.length === 0 && (
          <div style={{ padding: 20, color: "var(--muted)" }}>No customers</div>
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

      <Modal
        open={open}
        title={editing ? "Edit customer" : "Add customer"}
        onClose={() => setOpen(false)}
        onSubmit={save}
        submitText="Save"
      >
        <CustomerForm value={form} onChange={setForm} />
      </Modal>
    </>
  );
}
