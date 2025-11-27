import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
const API = import.meta.env.VITE_API_BASE || "http://localhost:8080/api";

export default function CustomerDetails(){
  const { id } = useParams();
  const [customer, setCustomer] = useState(null);
  const [orders, setOrders] = useState([]);

  useEffect(()=>{ load(); },[id]);

  async function load(){
    const c = await axios.get(`${API}/customers/${id}`);
    setCustomer(c.data);
    // use whichever endpoint you exposed
    try {
      const o = await axios.get(`${API}/customers/${id}/orders`);
      setOrders(o.data);
    } catch {
      const o = await axios.get(`${API}/orders`, { params:{ customerId:id } });
      setOrders(o.data);
    }
  }

  if (!customer) return <div>Loading…</div>;

  return (
    <div className="container">
      <div className="header">
        <h1 className="h1">{customer.firstName} {customer.lastName}</h1>
        <div className="badge">Customer #{customer.id}</div>
      </div>

      <div className="card">
        <div className="row">
          <div>Email: {customer.email}</div>
          <div className="right">Address: {customer.address}</div>
        </div>
      </div>

      <div className="card">
        <h3 style={{margin:"0 0 12px 0"}}>Orders</h3>
        <table className="table">
          <thead>
            <tr><th>ID</th><th>Date</th><th>Status</th><th>Total</th></tr>
          </thead>
          <tbody>
            {orders.length===0 && <tr><td>No orders</td></tr>}
            {orders.map(o=>(
              <tr key={o.id}>
                <td>{o.id}</td>
                <td>{o.orderDate}</td>
                <td>{o.status}</td>
                <td>${Number(o.totalAmount).toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
