import axios from "axios";
import { useState } from "react";

const Form = ({ setTeams }) => {
  const [teamName, setTeamName] = useState("");

  const api = "http://localhost:8080/cricket";

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      const respsone = await axios.post(api, { team_name: teamName });
      setTeams((prev) => [...prev, respsone.data]);
      setTeamName("");
    } catch (error) {
      console.error(error);
    }
  };

  const changeTeamName = (e) => {
    const target = e.target;
    setTeamName(target.value);
  };

  return (
    <form onSubmit={onSubmit} className="form">
      <div className="form__group">
        <label htmlFor="team-name" className="group__item form__label">
          Team Name:
        </label>
        <input
          type="text"
          name="team-name"
          id="team-name"
          placeholder="Team"
          value={teamName}
          onChange={changeTeamName}
          className="group__item form__input"
        />
      </div>
      <div className="form__group">
        <button type="submit" className="group__submit button--primary">
          Save
        </button>
      </div>
    </form>
  );
};
export default Form;
