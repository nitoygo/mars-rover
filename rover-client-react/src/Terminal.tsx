import React, {
  useState,
  useEffect,
  useRef,
  ChangeEvent,
  KeyboardEvent,
} from "react";

import { SendCommand } from "./lib/Comms";

interface Message {
  text: string;
}

export default function Dashboard() {
  const [messages, setMessages] = useState<Message[]>([]);
  const messagesEndRef = useRef<HTMLDivElement | null>(null);

  const handleSendMessage = async (message: string) => {
    const response = await SendCommand(message);
    if (response) {
      setMessages([...messages, { text: message }, { text: response }]);
    } else {
      setMessages([...messages, { text: message }]);
    }
  };

  useEffect(() => {
    // Scroll to the latest message
    if (messagesEndRef.current) {
      messagesEndRef.current.scrollIntoView({ behavior: "smooth" });
    }
  }, [messages]);

  // TODO: refactor into reusable components
  return (
    <>
      <div className="prompt-container">
        <div className="prompt-messages">
          {messages.map((msg, index) => (
            <div key={index} className="message">
              {msg.text}
            </div>
          ))}
          <div ref={messagesEndRef} />
        </div>
        <div className="prompt-input">
          <input
            type="text"
            placeholder="Type your command..."
            onKeyDown={(e) => {
              if (e.key === "Enter") {
                handleSendMessage(e.currentTarget.value);
                e.currentTarget.value = "";
              }
            }}
          />
        </div>
      </div>
    </>
  );
}
