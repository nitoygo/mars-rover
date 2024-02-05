import React from "react";
import { render, screen } from "@testing-library/react";
import App from "./App";

test("renders Type your command", () => {
  render(<App />);
  const linkElement = screen.getByText(/Type your command/i);
  expect(linkElement).toBeInTheDocument();
});
