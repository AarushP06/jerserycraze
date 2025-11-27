import { Routes, Route, Navigate } from "react-router-dom";
import Layout from "./components/Layout.jsx";
import CustomersPage from "./pages/CustomersPage.jsx";
import JerseysPage from "./pages/JerseysPage.jsx";
import CustomerDetails from "./pages/CustomerDetails.jsx";

export default function App() {
  return (
    <Routes>
      <Route element={<Layout />}>

        <Route index element={<Navigate to="/customers" replace />} />

        <Route path="/customers" element={<CustomersPage />} />
        <Route path="/customers/:id" element={<CustomerDetails />} />

        <Route path="/jerseys" element={<JerseysPage />} />

      </Route>
    </Routes>
  );
}




