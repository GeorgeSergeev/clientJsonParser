package com.alvion.importer.models;

public class ClientResult {
  private Client client;
  private ClientStatistic statistic;

  public ClientResult(Client client) {
    this.client = client;
    this.statistic = new ClientStatistic();
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public ClientStatistic getStatistic() {
    return statistic;
  }

  public void setStatistic(ClientStatistic statistic) {
    this.statistic = statistic;
  }

  @Override
  public String toString() {
    return "ClientResult{" +
            "client=" + client +
            ", statistic=" + statistic +
            '}';
  }
}
