import axios from "axios";
import { useRef, useState } from "react";
import { useTeamContext } from "../../context/TeamContext";
import "./form.css";

const Form = () => {
  const { setTeams } = useTeamContext();
  const [error, setError] = useState(undefined);
  const teamRef = useRef(null);
  const api = "http://localhost:8080/cricket";

  const onSubmitTeam = async (e) => {
    e.preventDefault();
    const { current } = teamRef;
    try {
      setError(undefined);
      if (!current.value) throw new Error("Team name cannot be empty");
      const response = await axios.post(api, { team_name: current.value });
      const { data } = response;
      setTeams((prev) => [...prev, { ...data, isPlaying: false }]);
      current.value = "";
    } catch (error) {
      if (error.request && error.request.status === 400) {
        setError("Team already exists");
      }
      console.error(error.message);
    }
  };

  const renderError = () => {
    if (!error) return;

    return <h3 className="error">{error}</h3>;
  };

  return (
    <>
      {renderError()}
      <form className="form" onSubmit={onSubmitTeam}>
        <div className="form__group">
          <label htmlFor="team-name" className="group__label">
            Team Name
          </label>
          <input
            type="text"
            id="team-name"
            name="team-name"
            className="group__input"
            placeholder="RCB"
            ref={teamRef}
          />
        </div>
        <div className="form__group">
          <button type="submit" className="button button--primary">
            Save Team
          </button>
        </div>
      </form>
    </>
  );
};

export default Form;
