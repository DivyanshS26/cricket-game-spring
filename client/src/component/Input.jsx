import { useState } from "react";
import axios from "axios";

const Input = ({ data, setters }) => {
  const { api, teams, teamId, teamName } = data;
  const { setTeams, setPlayingTeams } = setters;

  const [isUpdate, setIsUpdate] = useState(false);
  const [updateTeam, setUpdateTeam] = useState("");
  const [isPlayingTeam, setIsPlayingTeam] = useState(false);

  const onUpdate = async (e) => {
    const id = parseInt(e.target.dataset.teamid);
    if (!isUpdate) {
      setIsUpdate(true);
      return;
    }
    try {
      const updatedTeam = { teamId: id, team_name: updateTeam };
      await axios.put(api, updatedTeam);
      setTeams([...teams.filter((team) => team.teamId !== id), updatedTeam]);
      setIsUpdate(false);
    } catch (error) {
      console.error(error);
    }
  };

  const handleChange = (e) => {
    setIsPlayingTeam(!isPlayingTeam);
    if (!isPlayingTeam) {
      setPlayingTeams((prev) => [...prev, teamId]);
    } else {
      setPlayingTeams((prev) => prev.filter((id) => id !== teamId));
    }
  };

  return (
    <>
      <div>
        {isUpdate ? (
          <input
            type="text"
            name="team"
            id="team"
            value={updateTeam}
            onChange={(e) => setUpdateTeam(e.target.value)}
          />
        ) : (
          <div>
            <input type="checkbox" onChange={handleChange} />
            {teamName}
          </div>
        )}
      </div>
      {isUpdate ? (
        <button onClick={onUpdate} data-teamid={teamId}>
          Done
        </button>
      ) : (
        <button onClick={onUpdate} data-teamid={teamId}>
          Update
        </button>
      )}
    </>
  );
};

export default Input;
