import { http } from './http';

export const listCustomers = (page=0, size=5, search='') =>
  http.get('/customers', { params: { page, size, search } });

export const getCustomer = (id) => http.get(`/customers/${id}`);
export const getCustomerOrders = (id, page=0, size=5) =>
  http.get(`/customers/${id}/orders`, { params: { page, size } });

export const createCustomer = (data) => http.post('/customers', data);
export const updateCustomer = (id, data) => http.put(`/customers/${id}`, data);
export const deleteCustomer = (id) => http.delete(`/customers/${id}`);
