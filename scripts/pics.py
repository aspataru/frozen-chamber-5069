# sudo apt-get install python3-picamera
# pip install requests

import time
import picamera
import requests
import logging

w = 640
h = 480
url = 'http://frozen-chamber-5069.herokuapp.com/file/upload'
filename = '/tmp/foo.jpg'
logfile = '/tmp/pics.log'
logging.basicConfig(level=logging.INFO, filename=logfile, filemode="a+",
                    format="%(asctime)-15s %(levelname)-8s %(message)s")

with picamera.PiCamera() as camera:
    camera.resolution = (w, h)

    # Warm up camera
    logging.info("warming up camera...")
    time.sleep(2)

    n = 0
    
    while True:
        iteration_string = "%d: " % (n)
        logging.info(iteration_string + "capturing image " + filename)
        camera.capture(filename)

        files = {'file': open(filename,'rb')}
        
        logging.info(iteration_string + "posting image " + filename)
        r = requests.post(url, files=files)
        logging.info(iteration_string + "status = " + `r.status_code`
                     + " text= " + r.text)

        n += 1
        
