#Exercice 1
runif(5)
rnorm(5)
rnorm(5,10,1)
data.exp <- rexp(300,4)
data.pois <- rpois(300,2)
dnorm(0)
pnorm(0,0,5)
x <- seq(0,4,length=100)
hist(data.exp,freq=FALSE)
lines(x,dexp(x,4))
#Bonne modélisation R
plot(table(data.pois)/300)
lines(seq(0,11,by=0.1),dpois(seq(0,11,by=0.1),2))
#Bonne modélisation R

#Exercice 2
tclbernoulli<-function(N,p){
  res= c()
  for (i in seq(1,1000,by=1)){
    S<-((sum(rbinom(N,1,p))/N)-p)*sqrt(N)/(sqrt(p*(1-p)))
    res = c(res,S)
  }
  return (res)
}
Exp<-tclbernoulli(1000,0.2)
count<-0
for (i in Exp){
  if (i>=-1.96 && i<=1.96){
    count<- count+1
  }
}
count
#On observe que le pourcentage correspond à peu près à 95%
plot.ecdf(Exp)
lines(seq(-4,4,by=0.01),pnorm(seq(-4,4,by=0.01)))
#Théorème de la limite centrale?

#Exercice 3
Nfin <- 5000
X <- rexp(Nfin,2)
Y <- cumsum(X)/1:Nfin
plot(1:Nfin,Y,type = "l",ylim = c(0,1),xlab="n",ylab="moyenne empirique")
for (i in 2:50){
  X <- rexp(Nfin,2)
  Y <- cumsum(X)/1:Nfin
  lines(1:Nfin,Y,col = i)
}
#Ce programme trace les moyennes de 1 à 5000
#résultats d'expériences pouvant être modélisés par 
#une loi exponentielle de paramètre variant de 2 à 50
#Ainsi on constate que plus le nombre d'expériences augmente
#et plus on tend vers l'espérance de la variable aléatoire
#On illustre donc la loi des grands nombres

lgnexpo <- function(N){
  moy=rep(0,100)
  for (i in 1:100){
    moy[i]=mean(rexp(N,2))
  }
  return(moy)}
#Renvoie un tableau de 100 moyennes de N résultats associés
#à une variable aléatoire de paramètre 2

par(mfrow=c(1,3))
boxplot(lgnexpo(100))
boxplot(lgnexpo(1000))
boxplot(lgnexpo(10000))
#Plus la valeur de N est élevée et plus la variance empirique est faible
#Ce qui revient à dire que plus on s'éloigne de l'espérance
#Moins on a de chance d'obtenir ce résultat
#Ce qui se rapporte au théorème de Bienaymé-Tchebychev

lgncauchy<- function(N){
  moy=rep(0,100)
  for (i in 1:100){
    moy[i]=mean(rcauchy(N,0,2))
  }
  return(moy)}

par(mfrow=c(1,3))
boxplot(lgncauchy(100),outline=FALSE)
boxplot(lgncauchy(1000),outline=FALSE)
boxplot(lgncauchy(10000),outline=FALSE)
#Outline permet de retirer les valeurs abhérantes de la boîte
#Ainsi on remarque, que lorsque outline est true, on a plus de valeurs éloignées
#de la moyenne pour un grand nombre d'expériences
#On remarque enfin que lorsque outline est false, toutes les expériences
#ont globalement les mêmes valeurs remarquables

#Exercice 4
ech1=rbinom(1000,10,8/10)
ech2=rbinom(1000,20,8/20)
ech3=rbinom(1000,30,8/30)
ech4=rbinom(1000,100,8/100)

