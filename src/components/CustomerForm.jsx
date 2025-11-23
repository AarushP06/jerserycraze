import { useState, useEffect } from "react";

export default function CustomerForm({ value, onChange }) {
  const [form, setForm] = useState({ firstName:"", lastName:"", email:"", address:"" });

  useEffect(() => { if (value) setForm(v => ({...v, ...value})); }, [value]);

  function set(k, v){ const next = { ...form, [k]: v }; setForm(next); onChange?.(next); }

  return (
    <>
      <label>First name<input value={form.firstName} onChange={e=>set("firstName",e.target.value)} /></label>
      <label>Last name<input value={form.lastName} onChange={e=>set("lastName",e.target.value)} /></label>
      <label>Email<input value={form.email} onChange={e=>set("email",e.target.value)} /></label>
      <label>Address<input value={form.address} onChange={e=>set("address",e.target.value)} /></label>
    </>
  );
}
