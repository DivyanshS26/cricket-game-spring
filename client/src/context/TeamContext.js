import { createContext, useContext } from "react";

export const TeamContext = createContext(null);

export const useTeamContext = () => {
  const context = useContext(TeamContext);
  if (!context) throw new Error("Wrap useTeamContext inside TeamContext");
  return context;
};
