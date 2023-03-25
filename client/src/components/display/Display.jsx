import { useTeamContext } from "../../context/TeamContext";
import { Team } from "../team";
import "./display.css";

const Display = () => {
  const { teams } = useTeamContext();

  return (
    <div className="display">
      <ol className="display__list">
        {teams &&
          teams.map(({ teamId, team_name: teamName, isPlaying }, idx) => (
            <Team key={teamId} data={{ teamId, teamName, isPlaying, idx }} />
          ))}
      </ol>
    </div>
  );
};

export default Display;
