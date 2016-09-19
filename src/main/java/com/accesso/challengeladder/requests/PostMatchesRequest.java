package com.accesso.challengeladder.requests;

/**
 * @author oscar.lopez
 *
 *         Aug 11, 2016
 */
public class PostMatchesRequest
{

    private Integer creatorUserId;
    private Integer opponentUserId;

    public PostMatchesRequest(Integer creatorUserId, Integer opponentUserId)
    {
        this.creatorUserId = creatorUserId;
        this.opponentUserId = opponentUserId;
    }

    public Integer getCreatorUserId()
    {
        return creatorUserId;
    }

    public Integer getOpponentUserId()
    {
        return opponentUserId;
    }

    public void setCreatorUserId(Integer creatorUserId)
    {
        this.creatorUserId = creatorUserId;
    }

    public void setOpponentUserId(Integer opponentUserId)
    {
        this.opponentUserId = opponentUserId;
    }

    @Override
    public String toString()
    {
        return "PostMatchesRequest [creatorUserId=" + creatorUserId + ", opponentUserId=" + opponentUserId + "]";
    }
}