test <- function(x,y,N){
  z = x+y
  nomb = 0
  for (i in N){
    if ((i > x) & (i<y)){
      nomb = nomb + 1
    }
  }
  Ntilde = N
  n = dim(N)
  for (i in 1:n[1]){
    for (j in 1:n[2]){
      if (N[i,j]>=x){
        Ntilde[i,j] = x
      }
    }
  }
  sortie = list(z, nomb, Ntilde)
  return (sortie)
}