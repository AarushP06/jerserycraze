import { useState, useEffect } from "react";

export default function JerseyForm({ value, onChange }) {
  const [form, setForm] = useState({
    name:"", club:"", size:"M", price:0, inStock:true, type:"Home", imageLink:"", description:""
  });

  useEffect(() => { if (value) setForm(v => ({...v, ...value})); }, [value]);

  function set(k, v){ const next = { ...form, [k]: v }; setForm(next); onChange?.(next); }

  return (
    <>
      <label>Name<input value={form.name} onChange={e=>set("name",e.target.value)} /></label>
      <label>Club<input value={form.club} onChange={e=>set("club",e.target.value)} /></label>
      <div className="row">
        <label style={{flex:1}}>Size
          <select value={form.size} onChange={e=>set("size",e.target.value)}>
            <option>XS</option><option>S</option><option>M</option><option>L</option><option>XL</option>
          </select>
        </label>
        <label style={{flex:1}}>Type
          <select value={form.type} onChange={e=>set("type",e.target.value)}>
            <option>Home</option><option>Away</option><option>Third</option>
          </select>
        </label>
      </div>
      <div className="row">
        <label style={{flex:1}}>Price<input type="number" value={form.price} onChange={e=>set("price",Number(e.target.value))} /></label>
        <label style={{flex:1}}>In stock
          <select value={String(form.inStock)} onChange={e=>set("inStock",e.target.value==="true")}>
            <option value="true">Yes</option>
            <option value="false">No</option>
          </select>
        </label>
      </div>
      <label>Image URL<input value={form.imageLink} onChange={e=>set("imageLink",e.target.value)} /></label>
      <label>Description<input value={form.description} onChange={e=>set("description",e.target.value)} /></label>
    </>
  );
}
