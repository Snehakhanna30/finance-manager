import React, { useState } from "react";
import axios from "axios";

interface TransactionFormProps {
  onComplete?: () => void;
}

const TransactionForm: React.FC<TransactionFormProps> = ({ onComplete }) => {
  const [formData, setFormData] = useState({
    amount: "",
    type: "expense",
    category: "",
    date: "",
    description: "",
  });

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const payload = {
      ...formData,
      amount: parseFloat(formData.amount),
    };

    try {
      console.log("✅ Sending:", payload);

      await axios.post("http://localhost:8080/api/transactions", payload);

      alert("Transaction added!");
      setFormData({
        amount: "",
        type: "expense",
        category: "",
        date: "",
        description: "",
      });

      if (onComplete) onComplete(); // Refresh list
    } catch (error) {
      console.error("❌ Submission error:", error);
      alert("Error adding transaction");
    }
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4 p-4 bg-white rounded shadow">
      <input
        type="number"
        name="amount"
        placeholder="Amount"
        value={formData.amount}
        onChange={handleChange}
        className="w-full border p-2 rounded"
        required
      />

      <select
        name="type"
        value={formData.type}
        onChange={handleChange}
        className="w-full border p-2 rounded"
      >
        <option value="income">Income</option>
        <option value="expense">Expense</option>
      </select>

      <input
        type="text"
        name="category"
        placeholder="Category"
        value={formData.category}
        onChange={handleChange}
        className="w-full border p-2 rounded"
        required
      />

      <input
        type="date"
        name="date"
        value={formData.date}
        onChange={handleChange}
        className="w-full border p-2 rounded"
        required
      />

      <input
        type="text"
        name="description"
        placeholder="Description"
        value={formData.description}
        onChange={handleChange}
        className="w-full border p-2 rounded"
      />

      <button type="submit" className="bg-blue-600 text-white px-4 py-2 rounded">
        Add Transaction
      </button>
    </form>
  );
};

export default TransactionForm;
