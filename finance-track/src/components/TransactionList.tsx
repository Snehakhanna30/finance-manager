import React, { useEffect, useState } from "react";
import axios from "axios";

interface Transaction {
  id: number;
  amount: number;
  category: string;
  date: string;
  description: string;
  type: string;
}

const TransactionList = ({ refreshTrigger }: { refreshTrigger: number }) => {
  const [transactions, setTransactions] = useState<Transaction[]>([]);

  useEffect(() => {
    console.log("📥 Refresh Triggered:", refreshTrigger);

    axios
      .get("http://localhost:8080/api/transactions")
      .then((res) => {
        console.log("✅ Fetched:", res.data);
        setTransactions(res.data as Transaction[]);
      })
      .catch((err) => {
        console.error("❌ Fetch error:", err);
      });
  }, [refreshTrigger]);

  return (
    <div className="space-y-4">
      {transactions.map((tx) => (
        <div key={tx.id} className="p-2 border rounded">
          <div className="text-sm font-semibold">{tx.description}</div>
          <div className="text-sm text-gray-500">{tx.date}</div>
          <div className={`text-sm ${tx.type === "income" ? "text-green-600" : "text-red-600"}`}>
            {tx.type === "income" ? "+" : "-"}${tx.amount.toFixed(2)}
          </div>
          <div className="text-xs text-gray-400">{tx.category}</div>
        </div>
      ))}
    </div>
  );
};

export default TransactionList;
