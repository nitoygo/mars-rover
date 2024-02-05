import axios from "axios";

export const SendCommand = async (data: string) => {
  try {
    // TODO: refactor this
    // send to maneuver api if the command contains M|L|R
    if (/^[MLR]*$/.test(data)) {
      await axios.post("http://localhost:8080/maneuver", {
        data: data,
      });
    } else {
      // otherwise, treat as position command
      await axios.post("http://localhost:8080/position", {
        data: data,
      });
    }

    // query position
    const response = await axios.get("http://localhost:8080/position");

    return response.data.message;
  } catch (error) {
    console.log(error);
  }
  return null;
};
