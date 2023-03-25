import axios from "axios";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { useTeamContext } from "../../context/TeamContext";
import { useResultContext } from "../../context/ResultContext";
import { Loader } from "../loader";
import "./start.css";

const Start = () => {
  const api = "http://localhost:8080/cricket/startMatch";

  const { teams } = useTeamContext();
  const { setResults, results, setTournamentWinners, tournamentWinners } =
    useResultContext();

  const [isDisabled, setIsDisabled] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const onStartMatch = async (e) => {
    try {
      setIsLoading(true);
      const data = teams
        .filter(({ isPlaying }) => isPlaying)
        .map(({ teamId, team_name }) => ({ teamId, team_name }));

      const response = await axios.post(api, data);
      setResults((prev) => [...prev, response.data.match]);
      setTournamentWinners((prev) => [...prev, response.data.winningTeam]);
    } catch (error) {
      console.error(error.message);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    const numberOfPlayingTeams = teams.reduce(
      (acc, team) => acc + (team.isPlaying ? 1 : 0),
      0
    );
    const value = Math.log2(numberOfPlayingTeams);
    setIsDisabled(value === 0 || value % 1 !== 0);
  }, [teams]);

  return (
    <div className="start">
      <button
        className="button button--primary"
        disabled={isDisabled}
        onClick={onStartMatch}
      >
        <Loader isLoading={isLoading}>Start Match</Loader>
      </button>
      <details className="start__goto">
        <summary>Tournament Results</summary>
        {results &&
          results.map((_, id) => (
            <Link
              key={id}
              to={`/tournament/results/${id}`}
              className="button goto__tournament"
            >
              Tournament {id + 1} Winner:
              {tournamentWinners[id]}
            </Link>
          ))}
      </details>
    </div>
  );
};

export default Start;
