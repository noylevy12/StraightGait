<?php
header("Content-Type:application/json");
$legStatus= array(0,0,0,0,0); //initialized change reson =0 and degrree to 0 on right, left, up, down
$legCurrDegree = '0';
$file = 'currDegree.txt'; // the file simulate the database



if($_SERVER['REQUEST_METHOD'] == 'GET'){
    //echo "this is a GET request";
    if($_GET["requestName"] =="legMoved" ){
        echo "The leg moved " .$_GET["degree"]. "degree";
        $legCurrDegree = $_GET["degree"];
        //echo $legCurrDegree;
     //   exit();
    }
    else if($_GET["requestName"] =="getLegStatus" ){
        $legCurrDegree = file_get_contents($file);
        echo $legCurrDegree;
     //   exit();
    }
}

else if($_SERVER['REQUEST_METHOD'] == 'POST'){
    echo "this is a POST request\n";
    $legCurrDegree = file_get_contents($file);
    echo "before:" .$legCurrDegree ."\n";
    if($_POST["requestName"] =="legMoved" ){
        $legCurrDegree = $_POST["degree"];
        file_put_contents($file, $legCurrDegree);
        echo "after:" .$legCurrDegree ."\n";
    }
}

else if($_SERVER['REQUEST_METHOD'] == 'PUT'){
    echo "this is a PUT request";
    echo $legStatus;
    parse_str(file_get_contents("php://input"),$post_vars);
    if($post_vars["requestName"] =="putLegStatus" ){
        if($post_vars["changeReason"] == ( 1 || "r" || "right" || "RIGHT" )){ // the leg move right
            $legStatus= [1, $post_vars["degree"], 0, 0, 0];
            echo "the leg move" .$post_vars["degree"]. "degree right";
        }
        else if($_PUT["changeReason"] == ("2" || "l" || "left" || "LEFT" )){ // the leg move left
            $legStatus= [2,0,$_PUT["degree"],0,0];
        }
        else if($_PUT["changeReason"] == ("3" || "u" || "up" || "UP" )){ // the leg move up
            $legStatus= [3,0,0,$_PUT["degree"],0];
        }
        else if($_PUT["changeReason"] == ("4" || "d" || "down" || "DOWN" )){ // the leg move down
            $legStatus= [4,0,0,0,$_PUT["degree"]];
        }
    }
}

function response($status,$statusMessage,$data)
{
	header("HTTP/1.1 ".$status);
	
	$response['status']=$status;
	$response['status_message']=$status_message;
	$response['data']=$data;
	
	$json_response = json_encode($response);
	echo $json_response;
}
    
    


?>