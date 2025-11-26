import http from "./http";

export function listCustomers(params) {
  return http.get("/customers", { params });
}

export function getCustomer(id) {
  return http.get(`/customers/${id}`);
}

export function createCustomer(payload) {
  return http.post("/customers", payload);
}

export function updateCustomer(id, payload) {
  return http.put(`/customers/${id}`, payload);
}

export function deleteCustomer(id) {
  return http.delete(`/customers/${id}`);
}
