import "./loader.css";

const Loader = ({ isLoading, children }) => {
  if (!isLoading) return children;

  return (
    <div className="loader">
      <div className="loader__spinner" />
    </div>
  );
};

export default Loader;
