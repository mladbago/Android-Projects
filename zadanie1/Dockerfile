FROM ubuntu:22.04 
RUN apt update
RUN apt update && apt install tzdata -y
ENV TZ=Europę/Warsaw
RUN apt install software-properties-common -y
RUN add-apt-repository ppa:deadsnakes/ppa
RUN ln -s /usr/bin/pip3 /usr/bin/pip
RUN ln -s /usr/bin/python3.8 /usr/bin/python
RUN apt install python3.8 -y
RUN python3.8 --version
RUN apt-get -y install openjdk-8-jdk 
RUN rm /bin/sh && ln -s /bin/bash /bin/sh
RUN apt-get -qq -y install curl
RUN apt-get install -y unzip
RUN apt-get install -y zip
RUN curl -s https://get.sdkman.io | bash
RUN chmod a+x "$HOME/.sdkman/bin/sdkman-init.sh"
RUN source "$HOME/.sdkman/bin/sdkman-init.sh" && sdk install kotlin
RUN apt-get install -y wget 
RUN \
    cd /usr/local && \
    curl -L https://services.gradle.org/distributions/gradle-7.6-bin.zip -o gradle-7.6-bin.zip && \
    unzip gradle-7.6-bin.zip && \
    rm gradle-7.6-bin.zip

# Export some environment variables
ENV GRADLE_HOME=/usr/local/gradle-7.6
ENV PATH=$PATH:$GRADLE_HOME/bin

RUN mkdir MyFolder
COPY GradleProject /MyFolder/GradleProject
WORKDIR /MyFolder/GradleProject
RUN gradle build
RUN gradle run 
CMD gradle run; sleep infinity




RUN gradle -v


