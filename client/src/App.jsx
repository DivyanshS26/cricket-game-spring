import { useEffect, useState } from "react";
import { Routes, Route } from "react-router-dom";
import { Home, Results } from "./pages";
import { TeamContext } from "./context/TeamContext";
import { ResultContext } from "./context/ResultContext";
import axios from "axios";

const App = () => {
  const [teams, setTeams] = useState([]);
  const [results, setResults] = useState([]);
  const [tournamentWinners, setTournamentWinners] = useState([]);

  useEffect(() => {
    const getTeams = async () => {
      try {
        const response = await axios.get("http://localhost:8080/cricket");
        const { data } = response;
        if (!data) throw new Error("No teams found");
        setTeams(data.map((team) => ({ ...team, isPlaying: false })));
      } catch (error) {
        console.error(error.message);
      }
    };
    getTeams();
  }, []);

  return (
    <div className="container">
      <ResultContext.Provider
        value={{ results, setResults, tournamentWinners, setTournamentWinners }}
      >
        <TeamContext.Provider value={{ teams, setTeams }}>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/tournament/results/:id" element={<Results />} />
          </Routes>
        </TeamContext.Provider>
      </ResultContext.Provider>
    </div>
  );
};

export default App;
