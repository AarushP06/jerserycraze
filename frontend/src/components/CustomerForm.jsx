export default function CustomerForm({ value, onChange }) {
  const v = value || {};

  return (
    <div className="form">

      <label>
        First name
        <input
          value={v.firstName || ""}
          onChange={(e) => onChange({ ...v, firstName: e.target.value })}
        />
      </label>

      <label>
        Last name
        <input
          value={v.lastName || ""}
          onChange={(e) => onChange({ ...v, lastName: e.target.value })}
        />
      </label>

      <label>
        Email
        <input
          type="email"
          value={v.email || ""}
          onChange={(e) => onChange({ ...v, email: e.target.value })}
        />
      </label>

      <label>
        Address
        <input
          value={v.address || ""}
          onChange={(e) => onChange({ ...v, address: e.target.value })}
        />
      </label>

      <label>
        Password
        <input
          type="password"
          value={v.password || ""}
          onChange={(e) => onChange({ ...v, password: e.target.value })}
        />
      </label>

      <label>
        Phone
        <input
          value={v.phone || ""}
          onChange={(e) => onChange({ ...v, phone: e.target.value })}
        />
      </label>

    </div>
  );
}
