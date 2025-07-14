const express = require('express');
const app = express();
const port = 3000;



app.get('/pass',(req,res)=>
{
  res.send('password ramana')
})
app.get('/', (req, res) => {
  res.send(' Hello from Dockerized Node.js App with Jenkins This is to show2!');
});

app.listen(port, () => {
  console.log(`✅ Server is running at http://localhost:${port}`);
});

