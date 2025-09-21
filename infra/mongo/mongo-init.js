const db = db.getSiblingDB("ordersystem")

db.createUser(
    {
        user: "order-app",
        pwd: "qwerty",
        roles: [
            {
                role: "readWrite",
                db: "ordersystem"
            }
        ]
    }
);

db.createCollection('orders');
db.orders.insertOne({ createdAt: new Date(), userId: "1", description: "Test order" });