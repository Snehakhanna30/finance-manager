'use client' // Only if using Next.js — otherwise remove this line

import { useEffect, useState } from "react";
import axios from "axios";
import { Avatar } from "./ui/avatar";
import { Badge } from "./ui/badge";
import {
  ArrowDownIcon,
  ShoppingBag,
  Home,
  Car,
  Coffee,
  Utensils,
} from "lucide-react";

// Icon mapping based on category or type
const categoryIcons = {
  Food: ShoppingBag,
  Income: ArrowDownIcon,
  Housing: Home,
  Insurance: Car,
  Coffee: Coffee,
  Restaurant: Utensils,
};

export function RecentTransactions({ refreshTrigger }) {
  const [transactions, setTransactions] = useState([]);
  const [isClient, setIsClient] = useState(false);

  useEffect(() => {
    setIsClient(true);
  }, []);

  useEffect(() => {
    if (isClient) {
      axios
        .get("http://localhost:8080/api/transactions")
        .then((res) => {
          const sorted = [...res.data].sort((a, b) => new Date(b.date) - new Date(a.date));
          setTransactions(sorted.slice(0, 5));
        })
        .catch((err) => console.error("Error fetching transactions", err));
    }
  }, [isClient, refreshTrigger]); // refresh when new transaction is added

  if (!isClient) return null; // SSR-safe

  return (
    <div className="space-y-8">
      {transactions.map((txn) => {
        const Icon = categoryIcons[txn.category] || ArrowDownIcon; // Fallback icon
        return (
          <div key={txn.id} className="flex items-center">
            <Avatar className="h-9 w-9 border">
              <Icon className="h-4 w-4" />
            </Avatar>
            <div className="ml-4 space-y-1">
              <p className="text-sm font-medium leading-none">{txn.description}</p>
              <p className="text-sm text-muted-foreground">{txn.date}</p>
            </div>
            <div className="ml-auto font-medium">
              <span className={txn.amount > 0 ? "text-green-500" : "text-red-500"}>
                {txn.amount > 0 ? "+" : "-"}₹{Math.abs(txn.amount).toFixed(2)}
              </span>
            </div>
            <Badge variant="outline" className="ml-2">
              {txn.category}
            </Badge>
          </div>
        );
      })}
    </div>
  );
}
