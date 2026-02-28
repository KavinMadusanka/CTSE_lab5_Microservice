import express from 'express';

const app = express();

app.use(express.json());

let payments = [];
let idCounter = 1;

app.get('/payments', (req, res) => {
    res.json(payments);
});

app.post('/payments/process', (req, res) => {
    const payment = req.body;
    payment.id = idCounter++;
    payment.status = "SUCCESS";
    payments.push(payment);
    res.status(201).json(payment);
});

app.get('/payments/:id', (req, res) => {
    const payment = payments.find(p => p.id == req.params.id);
    if (!payment) return res.sendStatus(404);
    res.json(payment);
});

app.listen(8083, () => {
    console.log("Payment Service running on 8083");
});