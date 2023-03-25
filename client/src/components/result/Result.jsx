import { useEffect, useState } from "react";
import { useResultContext } from "../../context/ResultContext";

import "./result.css";

const Result = ({ resId }) => {
  const { results } = useResultContext();

  const [playingTeams, setPlayingTeams] = useState([]);
  const [scoreBoard, setScoreBoard] = useState([]);
  const [oversTable, setOversTable] = useState([]);
  const [winnerOfCurrentMatch, setWinnerOfCurrentMatch] = useState("");

  const onChangeOvers = (e, oversByTwo) => {
    setOversTable(scoreBoard[oversByTwo].scorecardList);
  };

  const onChangeMatches = (e, matchIndex) => {
    setWinnerOfCurrentMatch(results[resId][matchIndex].winner);
    setScoreBoard(results[resId][matchIndex].scoreboard);
    setOversTable(results[resId][matchIndex].scoreboard[0].scorecardList);
  };

  useEffect(() => {
    if (!results) return;

    setPlayingTeams(
      results[resId].map((result) => `${result.winner} vs ${result.loser}`)
    );

    setWinnerOfCurrentMatch(results[resId][0].winner);
    setScoreBoard(results[resId][0].scoreboard);
    setOversTable(results[resId][0].scoreboard[0].scorecardList);
  }, [results, resId]);

  return (
    <>
      <div>{results[resId].winningTeam}</div>
      <div className="result">
        <div className="result__teams">
          {playingTeams?.map((team, idx) => (
            <div className="teams__item" key={idx}>
              <button
                className="button"
                onClick={(e) => onChangeMatches(e, idx)}
              >
                {team}
              </button>
            </div>
          ))}
        </div>
        <div className="result__scoreboard">
          <div className="scoreboard__table__container">
            <div className="scoreboard">
              <div className="scoreboard__table table__header">
                <div className="table__item">Overs</div>
                <div className="table__item">Runs on Current Ball</div>
                <div className="table__item">Score</div>
                <div className="table__item">Wicket</div>
              </div>
            </div>
            {oversTable &&
              oversTable.map(({ over, runsOnCurrBall, score, wicket }, idx) => (
                <div className="scoreboard" key={idx}>
                  <div className="scoreboard__table">
                    <div className="table__item">{over}</div>
                    <div className="table__item">{runsOnCurrBall}</div>
                    <div className="table__item">{score}</div>
                    <div className="table__item">{wicket}</div>
                  </div>
                </div>
              ))}
          </div>
          <div className="scoreboard__button__container">
            {scoreBoard &&
              scoreBoard.map(({ scorecardList }, idx) => (
                <div className="scoreboard__button" key={idx}>
                  <button onClick={(e) => onChangeOvers(e, idx)}>
                    {idx + 1}
                    &nbsp;
                    {scorecardList[0].battingTeam.team_name}
                  </button>
                </div>
              ))}
          </div>
        </div>
      </div>
      <br />
      <div>
        Winning Team : <h4>{winnerOfCurrentMatch}</h4>
      </div>
    </>
  );
};

export default Result;
