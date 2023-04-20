import xml.etree.ElementTree as et
import sys
import subprocess
import time

class Test:
    def __init__(self, threads, rampTime, loop):
        self.threads = str(threads)
        self.rampTime = str(rampTime)
        self.loop = str(loop)


def execute(tests, PORT, tree, repeat):
    for test in tests:
        for i in range(repeat):
            tree = et.parse(FILE)
            tree.find(".//stringProp[@name='ThreadGroup.num_threads']").text = test.threads
            tree.find(".//stringProp[@name='ThreadGroup.ramp_time']").text = test.rampTime
            tree.find(".//stringProp[@name='LoopController.loops']").text = test.loop
            tree.write(FILE)

            output = subprocess.check_output(CMD, shell=True).decode('utf-8')
            outputPath = f'again/1sDelay/{PORT}/{test.threads}_{test.rampTime}_{test.loop}.csv'
            file = open(outputPath, "w")
            file.write(output)
            file.close()

            print(f'\t{test.threads}_{test.rampTime}_{test.loop}: port - {PORT} done {i+1}')

            # time.sleep(1)
        print(f'{test.threads}_{test.rampTime}_{test.loop}: port - {PORT} done')

if __name__ == '__main__':
    if(len(sys.argv) != 2):
        print(f'Usage: python3 {sys.argv[0]} testfile')
        exit(0)

    tests = []
    tests.append(Test(100, 1, 10))
    tests.append(Test(200, 1, 10))
    tests.append(Test(300, 1, 10))
    tests.append(Test(500, 1, 10))
    tests.append(Test(500, 10, 40))
    tests.append(Test(500, 10, 100))
    tests.append(Test(500, 10, 200))
    tests.append(Test(500, 10, 400))
    tests.append(Test(800, 10, 500))
    tests.append(Test(1000, 10, 500))
    tests.append(Test(1000, 10, 700))
    tests.append(Test(1000, 10, 1000))

    FILE = sys.argv[1]
    CMD = f'jmeter -n -t {FILE}'

    PORTS = ['9292', '9393']

    for PORT in PORTS:
        print(f'\nTests for port - {PORT} started')
        tree = et.parse(FILE)
        tree.find(".//stringProp[@name='HTTPSampler.port']").text = PORT
        tree.write(FILE)

        execute(tests, PORT, tree, 1)

        print(f'\nAll Tests for port - {PORT} finished')