import { NavLink, Outlet } from "react-router-dom";

export default function Layout(){
  return (
    <div className="app">
      <aside className="sidebar">
        <div className="brand">
          <span className="dot" />
          Jersey Craze
        </div>

        <nav className="nav">
          <NavLink to="/customers" className={({isActive})=>isActive?"active":""}>
            <span>Customers</span>
          </NavLink>
          <NavLink to="/jerseys" className={({isActive})=>isActive?"active":""}>
            <span>Jerseys</span>
          </NavLink>
        </nav>
      </aside>

      <main className="main">
        <Outlet />
      </main>
    </div>
  );
}
