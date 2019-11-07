#Exercice 1
A <- matrix(c(4,4,4,5,5,5,6,6,6), nrow = 3, ncol = 3)
B <- rbind(A, c(4,5,6))
dim(B)

#Exercice 2
A <- c(1,4,5)
Mat <- matrix(c(1,1,1,1,1,1,1,1,1,1,1,1), ncol = 4, nrow = 3)
l1 <- list(A, Mat)
L <- list(VectdeL=A, MatdeL=Mat, ListdeL=l1)

#Exercice 3
Mat <- matrix(seq(from = 1, to = 9, by = 1), ncol = 3, nrow = 3)
eigen(Mat)$vectors[,1]

#Exercice 4
tableau <- read.table(file= "C:/Users/charl/IFF1103/cathedral.txt", header = TRUE,
                           sep = "\t", row.names = 1)

#Exercice 5
A <- matrix(c(4,4,4,5,5,5,6,6,6), nrow = 3, ncol = 3)
B <- rbind(A, c(4,5,6))
B%*%A
A%*%B
A%*%t(B)

#Exercice 6
sum(A)
sum(A[,1])
max(A[2,])

#Exercice 7
x <- choose(6,0:6)
sum(x)
max(x)
which.max(x)
sort(X)

#Exercice 8
source("ScriptDeMaFonction.R")
sortie=test(4.3,5.4,A)
sortie

#Exercice 9
variance <- function(x){
  xbarre <- sum(x)/length(x)
  res <- 0
  for (i in x){
    res <- res + ((i-xbarre)^2)/(length(x)-1)
  }
  return(res)
}
variance(c(4,7,8,9,1))
var(c(4,7,8,9,1))

#Exercice 10
compte <- function(sequence,lettre){
  count <- 0
  for (i in sequence){
    if (i == lettre){
      count <- count + 1
    }
  }
  return(count)
}
sequence<-c("a","a","t","g","a","g","c","t","a","g","c","t","g")
compte(sequence,"a")
tab <- c("a","c","g","t")
liste <- c(0,0,0,0)
for(lettre in 1:4){
  liste[lettre] <- compte(sequence,tab[lettre])
}
print(liste)
