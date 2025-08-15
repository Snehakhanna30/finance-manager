"use client"

import { useState } from "react"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "./ui/tabs"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "./ui/card"
import { Overview } from "./Overview"
import { RecentTransactions } from "./RecentTransactions"
import { AccountSummary } from "./AccountSummary"
import { FinancialGoals } from "./FinancialGoals"
import { AddTransactionForm } from "./AddTransactionForm"
import TransactionList from "./TransactionList"

import { Button } from "./ui/button"
import { Plus } from "lucide-react"
import { LoansOverview } from "./LoansOverview"
import { SavingsOverview } from "./SavingsOverview"

export default function DashboardPage() {
  const [showAddTransaction, setShowAddTransaction] = useState(false)
  const [refreshTrigger, setRefreshTrigger] = useState(0);

  return (
    <div className="flex min-h-screen w-full flex-col">
      <main className="flex flex-1 flex-col gap-4 p-4 md:gap-8 md:p-8">
        <div className="flex items-center justify-between">
          <h1 className="text-2xl font-bold tracking-tight">Finance Dashboard</h1>
          <Button onClick={() => setShowAddTransaction(!showAddTransaction)}>
            <Plus className="mr-2 h-4 w-4" />
            Add Transaction
          </Button>
        </div>

        {showAddTransaction && (
          <Card className="mb-4">
            <CardHeader>
              <CardTitle>Add New Transaction</CardTitle>
              <CardDescription>Record a new expense or income</CardDescription>
            </CardHeader>
            <CardContent>
              <AddTransactionForm
  onComplete={() => {
    setShowAddTransaction(false);
    setRefreshTrigger((prev) => prev + 1); // ✅ force refresh
  }}
/>
            </CardContent>
          </Card>
        )}

        <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-4">
          <Card>
            <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
              <CardTitle className="text-sm font-medium">Total Balance</CardTitle>
            </CardHeader>
            <CardContent>
              <div className="text-2xl font-bold">12,580.00</div>
              <p className="text-xs text-muted-foreground">+1,245.00 from last month</p>
            </CardContent>
          </Card>
          <Card>
            <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
              <CardTitle className="text-sm font-medium">Monthly Income</CardTitle>
            </CardHeader>
            <CardContent>
              <div className="text-2xl font-bold">4,395.00</div>
              <p className="text-xs text-muted-foreground">+2.5% from last month</p>
            </CardContent>
          </Card>
          <Card>
            <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
              <CardTitle className="text-sm font-medium">Monthly Expenses</CardTitle>
            </CardHeader>
            <CardContent>
              <div className="text-2xl font-bold">2,860.00</div>
              <p className="text-xs text-muted-foreground">+18.1% from last month</p>
            </CardContent>
          </Card>
          <Card>
            <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
              <CardTitle className="text-sm font-medium">Savings Rate</CardTitle>
            </CardHeader>
            <CardContent>
              <div className="text-2xl font-bold">34.9%</div>
              <p className="text-xs text-muted-foreground">-4% from last month</p>
            </CardContent>
          </Card>
        </div>

        <Tabs defaultValue="overview" className="space-y-4">
          <TabsList>
            <TabsTrigger value="overview">Overview</TabsTrigger>
            <TabsTrigger value="accounts">Accounts</TabsTrigger>
            <TabsTrigger value="loans">Loans</TabsTrigger>
            <TabsTrigger value="savings">Savings</TabsTrigger>
            <TabsTrigger value="goals">Goals</TabsTrigger>
          </TabsList>
          <TabsContent value="overview" className="space-y-4">
            <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-7">
              <Card className="lg:col-span-4">
                <CardHeader>
                  <CardTitle>Financial Overview</CardTitle>
                </CardHeader>
                <CardContent className="pl-2">
                  <Overview />
                </CardContent>
              </Card>
              <Card className="lg:col-span-3">
                <CardHeader>
                  <CardTitle>Recent Transactions</CardTitle>
                  <CardDescription>You made 12 transactions this month.</CardDescription>
                </CardHeader>
                {/* <CardContent>
                  <RecentTransactions />
                </CardContent> */}
                <CardContent>
                <TransactionList />
                <RecentTransactions refreshTrigger={refreshTrigger} />
                 </CardContent>
              </Card>
            </div>
          </TabsContent>
          <TabsContent value="accounts" className="space-y-4">
            <AccountSummary />
          </TabsContent>
          <TabsContent value="loans" className="space-y-4">
            <LoansOverview />
          </TabsContent>
          <TabsContent value="savings" className="space-y-4">
            <SavingsOverview />
          </TabsContent>
          <TabsContent value="goals" className="space-y-4">
            <FinancialGoals />
          </TabsContent>
        </Tabs>
      </main>
    </div>
  )
}
