<?php
/**
 * Created by PhpStorm.
 * User: Vladyslav Yazykov
 * Date: 1/20/2018
 * Time: 3:53 AM
 */
foreach (glob('models/*.php') as $filename) {
    require $filename;
}

// 1. Get performance id
$performanceId = $_GET['performanceId'];

// 2. Get performance description from DB
$description =
    $performanceId == 0 ? 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
  Quisque cursus dignissim feugiat. Quisque sit amet neque at odio ullamcorper convallis nec eu orci. 
  Cras pulvinar rhoncus leo id fringilla. Phasellus pretium aliquam enim id tincidunt. 
  Integer venenatis lobortis venenatis. Pellentesque euismod egestas purus a euismod. 
  Pellentesque iaculis, urna id rhoncus egestas, elit magna dignissim ligula, at bibendum justo sem id turpis.
   Nulla risus felis, rutrum at dolor eget, fringilla vulputate ex. Nunc consectetur at nibh sed pharetra. 
   In convallis eleifend lorem, et rhoncus massa volutpat ac. Integer fermentum nisl eu dui aliquam porttitor.' :
        $performanceId == 1 ? 'Aliquam erat volutpat. Ut placerat mi elit, id euismod libero interdum nec. 
    Pellentesque iaculis nunc sed felis rutrum tristique. Aliquam pulvinar justo magna, in sodales tortor 
    fringilla vitae. Duis turpis nisi, accumsan id nunc nec, ornare volutpat lectus. Ut iaculis hendrerit ligula.
     Fusce sagittis sed neque a cursus.' :
            $performanceId == 2 ? 'Nullam at elit at sapien blandit varius. Maecenas sed odio sed est scelerisque aliquet ac
       consequat nisl. Pellentesque sit amet blandit nibh. Ut ut venenatis est. Donec vehicula, justo nec ultricies 
       auctor, lorem diam rhoncus nunc, eu volutpat justo erat a massa. Class aptent taciti sociosqu ad litora torquent 
       per conubia nostra, per inceptos himenaeos. Etiam id vehicula arcu, at laoreet mi. Aliquam scelerisque sapien et
        nisl interdum, in blandit purus porttitor. Vestibulum id tristique nisl, a tempor massa. Donec ut elementum 
        libero, et rhoncus erat. Maecenas et nulla rhoncus metus tempor varius. Praesent elit eros, vestibulum at 
        pretium vitae, auctor aliquet felis. Maecenas vestibulum leo ipsum, vel ullamcorper sapien euismod quis.'
                : 'Nullam eu nisl odio. Duis eget dolor vel ex tristique suscipit vitae id elit. Suspendisse non nunc at massa 
        tristique aliquam. In at mauris eu dui laoreet convallis tincidunt eu augue. Suspendisse eros odio, scelerisque
         feugiat blandit sed, ullamcorper aliquam tellus. Curabitur mollis interdum tortor ultricies pharetra. Ut augue 
         neque, eleifend id lorem eget, convallis fermentum mi. Class aptent taciti sociosqu ad litora torquent per 
         conubia nostra, per inceptos himenaeos. Fusce cursus elit non orci mattis fermentum. Vestibulum quis odio 
         ligula.';
$sessions = [
    new Session(date(DATE_ATOM, mktime(0, 0, 0, 7, 10, 2000)), null),
    new Session(date(DATE_ATOM, mktime(0, 15, 0, 7, 10, 2000)), null),
    new Session(date(DATE_ATOM, mktime(0, 30, 0, 7, 10, 2000)), null),
    new Session(date(DATE_ATOM, mktime(0, 30, 0, 7, 10, 2000)), null),
    new Session(date(DATE_ATOM, mktime(0, 0, 0, 7, 10, 2000)), null),
    new Session(date(DATE_ATOM, mktime(2, 45, 0, 7, 5, 2000)), null),
    new Session(date(DATE_ATOM, mktime(0, 0, 0, 7, 5, 2000)), null),
    new Session(date(DATE_ATOM, mktime(15, 0, 0, 7, 5, 2000)), null),
    new Session(date(DATE_ATOM, mktime(2, 0, 0, 7, 5, 2000)), null),
    new Session(date(DATE_ATOM, mktime(1, 30, 0, 7, 5, 2000)), null),
    new Session(date(DATE_ATOM, mktime(0, 30, 0, 7, 5, 2000)), null),
    new Session(date(DATE_ATOM, mktime(18, 0, 0, 7, 5, 2000)), null),
    new Session(date(DATE_ATOM, mktime(3, 45, 0, 7, 5, 2000)), null),
    new Session(date(DATE_ATOM, mktime(1, 15, 0, 7, 5, 2000)), null),
];

// 3. Send response
class PerformanceResponse
{
    public $description;
    public $sessions;
}

$response = new PerformanceResponse();
$response->sessions = $sessions;
$response->description = $description;

header("Access-Control-Allow-Origin: *");
echo(json_encode($response));
