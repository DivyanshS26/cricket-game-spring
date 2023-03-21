import axios from "axios";

const StartMatch = ({ api, teams }) => {
  const onStart = async () => {
    for (let i = 0; i < Math.log2(teams.length); i++) {
      await axios.get(api + "/startmatch");
    }
  };

  return (
    <div>
      <button onClick={onStart}>Start</button>
    </div>
  );
};

export default StartMatch;
