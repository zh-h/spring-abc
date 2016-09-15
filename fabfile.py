# -*- coding: utf-8 -*-
from fabric.api import *
from datetime import datetime

env.user = 'root'
env.hosts = ['spring-abc.xyz:29244']
shutdown = '../../bin/shutdown.sh'
startup = '../../bin/startup.sh'
target_classes_dir = './target/springabc'
web_root_dir = '../../webapps/ROOT/'

def update():
    # 使用 ./ 表示当前目录
    local('git pull')

def install():
    local('mvn install -DskipTests')

def reboot():
    local(shutdown)
    local(startup)

def startup():
    local(startup)

def shutdown():
    local(shutdown)

def deploy():
    '''
    部署文件
    '''
    local(shutdown)
    install()
    
    local('rm -rf %s* ' % web_root_dir)
    local('mv %s/* %s' % (target_classes_dir, web_root_dir))