import { http } from './http';

export const listOrders = (page=0, size=5, search='') =>
  http.get('/orders', { params: { page, size, search } });

export const getOrder = (id) => http.get(`/orders/${id}`);
export const createOrder = (data) => http.post('/orders', data);
// data example: { customerId, status, items:[{ jerseyId, quantity, unitPrice }] }
export const updateOrder = (id, data) => http.put(`/orders/${id}`, data);
export const deleteOrder = (id) => http.delete(`/orders/${id}`);
