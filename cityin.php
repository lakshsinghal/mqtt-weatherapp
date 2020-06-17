<!DOCTYPE html>
<html>
<head><title>Check Current Weather</title></head>
<style>
    body {text-align: center;
  background-image: url('sky.jpg');
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 100% 100%;
}
</style>
<body>
  <form method="post"><br><br><br><br>
    <h1><u>
    Enter City Name
    </u></h1>
    <input type="text" name="textdata"><br><br>
    <input type="submit" name="submit"><br><br><br><br>
    <a href="http://localhost/cityout.php"><h2>Weather Status!</h2></a>
    
  </form>
</body>
</html>
<?php
              
if(isset($_POST['textdata']))
{
$data=$_POST['textdata'];
$fp = fopen('cityname.txt', 'w');
fwrite($fp, $data);
fclose($fp);
}
?>