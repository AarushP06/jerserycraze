import { useEffect, useState } from "react";
import api from "../api/http";
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
    const res = await api.get("/customers", {
      params: { q, page, size: PAGE_SIZE }
    });

    const data = res.data;
    const list = Array.isArray(data.content) ? data.content : [];

    setRows(list);

    setMeta({
      page: data.number ?? 0,
      totalPages: data.totalPages ?? 1
    });
  }

  function openAdd() {
    setEditing(null);
    setForm({
      firstName: "",
      lastName: "",
      email: "",
      phone: "",
      address: "",
      password: ""
    });
    setOpen(true);
  }

  function openEdit(row) {
    setEditing(row);
    setForm({
      ...row,
      address: row.address || "",
      password: row.password || ""
    });
    setOpen(true);
  }

  async function save() {
    if (editing) {
      await api.put(`/customers/${editing.id}`, form);
    } else {
      await api.post("/customers", form);
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

      <div className="card">
        <table className="table">
          <thead>
            <tr>
              <th>Name</th>
              <th>Email</th>
              <th>Phone</th>
              <th style={{ textAlign: "right" }}>Actions</th>
            </tr>
          </thead>
          <tbody>
            {rows.map((c) => (
              <tr key={c.id}>
                <td>{c.firstName} {c.lastName}</td>
                <td>{c.email}</td>
                <td>{c.phone || "-"}</td>
                <td style={{ textAlign: "right" }}>
                  <button onClick={() => openEdit(c)}>Edit</button>{" "}
                  <button className="btn-danger" onClick={() => remove(c.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}

            {rows.length === 0 && (
              <tr>
                <td colSpan="4" style={{ padding: 16, color: "var(--muted)" }}>
                  No items
                </td>
              </tr>
            )}
          </tbody>
        </table>

        <div className="pagination">
          <button onClick={() => setPage(0)}>Back to Page 1</button>
          <button onClick={() => setPage((p) => Math.max(0, p - 1))}>Prev</button>
          <span className="badge">Page {meta.page + 1}</span>
          <button onClick={() => setPage((p) => Math.min(meta.totalPages - 1, p + 1))}>Next</button>
        </div>
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
