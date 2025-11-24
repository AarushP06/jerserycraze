export default function Modal({ open, title, onClose, onSubmit, submitText="Save", children }) {
  if (!open) return null;
  return (
    <div style={{position:"fixed",inset:0,background:"rgba(0,0,0,.5)",display:"grid",placeItems:"center",zIndex:50}}>
      <div className="card" style={{width:520,maxWidth:"90vw"}}>
        <div className="header" style={{marginBottom:12}}>
          <h2 className="h1" style={{fontSize:20}}>{title}</h2>
          <div className="toolbar">
            <button className="ghost" onClick={onClose}>Cancel</button>
            <button onClick={onSubmit}>{submitText}</button>
          </div>
        </div>
        <div style={{display:"grid",gap:12}}>
          {children}
        </div>
      </div>
    </div>
  );
}
