<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "city";

// Create connection in mysqli
$conn = mysqli_connect($servername, $username, $password, $dbname);
//Check connection in mysqli

if(!$conn){
	die("Error on the connection" .mysqli_error());
}
else
{
	echo "
	<head><title>Current Weather</title></head>
 
 <style>
body {
  background-image: url('sky.jpg');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 100% 100%;
}
</style>

<style>
h2 {text-align: center;} 
h3 {text-align: center;} 
h4 {text-align: center;} 
p {text-align: center;}  
</style>
<body>

<h2>
Weather Database Connected!
</h2>
";

}

$sql = "SELECT * FROM temprature";


$result = $conn->query($sql);

if ($result->num_rows > 0) {
  // output data of each row
  while($row = $result->fetch_assoc()) {
    echo "
    <h3>City Name: " . $row["name"]. "</h3>
    <p>
    <b>Minimum Temperature:</b> " . $row["min"]. "<sup> o</sup>C<br>
    <b>Maximum Temperature:</b> " . $row["max"]. "<sup> o</sup>C<br>
    <b>Pressure:</b> " . $row["Pressure"] . " hPa<br>
    <b>Humidity:</b> " . $row["Humidity"] . "% <br>
    <b>Wind Speed:</b> " . $row["Wind_speed"] . " kph <br>
    <b>Wind Direction:</b> " . $row["Wind_direction"] . "<sup> o</sup>  from North <br>
    <b>Cloud Percentage:</b> " . $row["Cloud%"] . "% <br>

    </p>
    <br>";
  }
} else {
  echo "0 results";
}

echo "<h2><u>Developers</u></h2>";
echo "<h4>Laksh Singhal and Pranshu Chakrawarty</h4>";

echo "</body>";
$conn->close();
?>