import { http } from './http';
export const searchJerseys = (q='') => http.get('/jerseys', { params: { search: q, page:0, size:10 } });
