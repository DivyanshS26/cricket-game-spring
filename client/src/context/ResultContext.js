import { createContext, useContext } from "react";

export const ResultContext = createContext(null);

export const useResultContext = () => {
  const context = useContext(ResultContext);
  if (!context) throw new Error("Wrap useTeamContext inside TeamContext");
  return context;
};
