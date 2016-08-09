package com.accesso.challengeladder.utils;

import spark.ResponseTransformer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class JsonUtil
{

	public static String toJson(Object object)
	{
		return new Gson().toJson(object);
	}

	public static ResponseTransformer json()
	{
		return JsonUtil::toJson;

	}

	public static <T> T fromJson(JsonElement json, Class<T> classIn)
	{
		return new Gson().fromJson(json, classIn);
	}
}