import { useEffect, useState } from 'react'
import { JerseysApi } from '../api/jerseys'

export default function JerseysPage() {
  const [page, setPage] = useState(0)
  const [rows, setRows] = useState([])
  const [search, setSearch] = useState('')

  async function load() {
    const data = await JerseysApi.list({ page, size: 5, search })
    const content = Array.isArray(data) ? data : (data.content ?? [])
    setRows(content)
  }

  useEffect(() => { load().catch(console.error) }, [page, search])

  return (
    <div>
      <header style={{ display: 'flex', justifyContent: 'space-between', marginBottom: 12 }}>
        <h2>Jerseys</h2>
        <div>
          <input
            placeholder="Search"
            value={search}
            onChange={e => { setPage(0); setSearch(e.target.value) }}
          />
          <button onClick={() => alert('open Add modal')}>Add</button>
        </div>
      </header>

      <table width="100%" cellPadding="8">
        <thead>
          <tr>
            <th>Name</th><th>Club</th><th>Type</th><th>Size</th><th>Price</th><th>Stock</th><th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {rows.map(r => (
            <tr key={r.id}>
              <td>{r.name}</td>
              <td>{r.club}</td>
              <td>{r.type}</td>
              <td>{r.size}</td>
              <td>{r.price}</td>
              <td>{r.inStock ? 'Yes' : 'No'}</td>
              <td>
                <button onClick={() => alert(`edit ${r.id}`)}>Edit</button>
                <button onClick={() => JerseysApi.remove(r.id).then(load)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <div style={{ marginTop: 12, display: 'flex', gap: 8 }}>
        <button disabled={page === 0} onClick={() => setPage(p => Math.max(0, p - 1))}>Prev</button>
        <button onClick={() => setPage(p => p + 1)}>Next</button>
      </div>
    </div>
  )
}
