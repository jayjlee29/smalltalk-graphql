// src/pages/Greeting.js
import React, { useEffect } from "react";
import axiosInstance from "../../api/axiosInstance";
import { GREETING_QUERY } from "../../graphql/queries";

const Greeting: React.FC = (): JSX.Element => {
  useEffect(() => {
    const fetchGreeting = async () => {
      try {
        const response = await axiosInstance.post("", {
          query: GREETING_QUERY
        });
        const greetingMessage = response.data.data.greeting;
        console.log(greetingMessage);
      } catch (error) {
        console.error("Error fetching greeting:", error);
      }
    };

    fetchGreeting();
  }, []);

  return (
    <div>
      <h1>Greeting Page</h1>
      <p>Check the console for the greeting message!</p>
    </div>
  );
};

export default Greeting;
