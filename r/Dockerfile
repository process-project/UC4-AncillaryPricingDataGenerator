FROM quantumobject/docker-rstudio

ENV cran_repo "http://cran.wu.ac.at/"


## Install prerequisite of h2o
# partially taken from https://github.com/h2oai/h2o-3/blob/master/Dockerfile
#RUN apt-get update && apt-get install -y libcurl4-openssl-dev libssl-dev
#apt-get update &&
RUN apt-get update && DEBIAN_FRONTEND=noninteractive apt-get install -y -q r-base \
                                              r-base-dev \
                                              gdebi-core \
                                              libapparmor1  \
                                              sudo \
                                              libcurl4-openssl-dev \
                                              libssl1.0.0 \


              && apt-get clean

RUN DEBIAN_FRONTEND=noninteractive apt install -y software-properties-common
RUN   add-apt-repository -y ppa:webupd8team/java && \
      apt-get update -q && \
      echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
      echo debconf shared/accepted-oracle-license-v1-1 seen true | debconf-set-selections && \
      DEBIAN_FRONTEND=noninteractive apt-get install -y oracle-java8-installer && \
      apt-get clean


# Install packages that H2O depends on.
# RUN Rscript -e "if (! ('methods'  %in% rownames(installed.packages()))) { install.packages('methods',  repos='$cran_repo' ) }" && \
#    Rscript -e "if (! ('statmod'  %in% rownames(installed.packages()))) { install.packages('statmod',  repos='$cran_repo' ) }" && \
#    Rscript -e "if (! ('stats'    %in% rownames(installed.packages()))) { install.packages('stats',    repos='$cran_repo' ) }" && \
#    Rscript -e "if (! ('graphics' %in% rownames(installed.packages()))) { install.packages('graphics', repos='$cran_repo' ) }" && \
#    Rscript -e "if (! ('RCurl'    %in% rownames(installed.packages()))) { install.packages('RCurl',    repos='$cran_repo' ) }" && \
#    Rscript -e "if (! ('jsonlite' %in% rownames(installed.packages()))) { install.packages('jsonlite', repos='$cran_repo' ) }" && \
#    Rscript -e "if (! ('tools'    %in% rownames(installed.packages()))) { install.packages('tools',    repos='$cran_repo' ) }" && \
#    Rscript -e "if (! ('tools'    %in% rownames(installed.packages()))) { install.packages('ggplot2',  repos='$cran_repo' ) }" && \
#    Rscript -e "if (! ('utils'    %in% rownames(installed.packages()))) { install.packages('utils',    repos='$cran_repo' ) }" && \
#
## Prepair R to run the examples
##apt-get install -y libssl-dev && \
#
#    Rscript -e "install.packages(c('base64enc'   , 'tibble'  , 'rmarkdown', 'markdown' , 'caTools'),   repos='$cran_repo') " && \
#    Rscript -e "install.packages(c('evaluate'    , 'digest'  , 'formatR'  , 'highr'    , 'knitr'  ),   repos='$cran_repo') " && \
#    Rscript -e "install.packages(c('stringr'     , 'yaml'    , 'Rcpp'     , 'htmltools', 'rattle' ),   repos='$cran_repo') " && \
#    Rscript -e "install.packages(c('rpart.plot'  , 'partykit', 'caret'    , 'party'    , 'pROC'   ),   repos='$cran_repo') " && \
RUN  Rscript -e "install.packages(c('plotly', 'RJDBC', 'colorspace', 'config', 'dplyr'),   repos='$cran_repo') "
#RUN  Rscript -e "install.packages(c('RJDBC', 'rJava'),   repos='$cran_repo') "

#USER guest
#RUN cd /home/guest && \
#    wget https://codeload.github.com/a-ghorbani/h2o_examples/zip/master -O h2o_examples.zip && \
#    unzip h2o_examples.zip && \
#    rm h2o_examples.zip
#USER root

EXPOSE 8787
EXPOSE 54321
EXPOSE 8080
