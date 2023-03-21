import { useState, useEffect } from "react";
import axios from "axios";
import { Routes, Route } from "react-router-dom";
import { Form, DisplayTeam, StartMatch } from "./component";

const App = () => {
  const [teams, setTeams] = useState([]);

  const api = "http://localhost:8080/cricket";

  useEffect(() => {
    const getTeams = async () => {
      try {
        const response = await axios.get(api);
        const { data } = response;
        setTeams(data);
      } catch (error) {
        console.error(error);
      }
    };
    getTeams();
  }, []);

  return (
    <div className="app">
      <Form setTeams={setTeams} />
      <DisplayTeam teams={teams} setTeams={setTeams} />
      <StartMatch api={api} teams={teams} />
    </div>
  );
};

export default App;
