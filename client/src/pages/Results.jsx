import { useParams } from "react-router-dom";
import { Result } from "../components/result";

const Results = () => {
  const { id } = useParams();
  return <Result resId={parseInt(id)} />;
};

export default Results;
