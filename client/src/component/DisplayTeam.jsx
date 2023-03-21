import axios from "axios";
import { useState } from "react";
import Input from "./Input";

const DisplayTeam = ({ teams, setTeams }) => {
  const api = "http://localhost:8080/cricket";
  const [playingTeams, setPlayingTeams] = useState([]);

  const onDelete = async (e) => {
    const id = parseInt(e.target.dataset.teamid);
    try {
      await axios.delete(api + "/" + id);
      setTeams(teams.filter((team) => team.teamId !== id));
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <ol>
        {teams &&
          teams.map(({ teamId, team_name }) => (
            <li key={teamId}>
              <div>
                <Input
                  data={{ api, teams, teamId, teamName: team_name }}
                  setters={{ setTeams, setPlayingTeams }}
                />
                <button onClick={onDelete} data-teamid={teamId}>
                  Delete
                </button>
              </div>
            </li>
          ))}
      </ol>
    </div>
  );
};

export default DisplayTeam;
