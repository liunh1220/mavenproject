package com.example.mail.model;

import java.util.List;
import java.util.Map;

public class SendMailInfo implements Cloneable
{
    private String subject;
    private String from;
    private String fromTitle;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String text;
    private List<String> files;
    private String vmTemplate;
    private Map<String, Object> model;
    private String encoding = "UTF-8";

    public String getEncoding()
    {
      return this.encoding;
    }

    public Map<String, Object> getModel()
    {
      return this.model;
    }

    public void setModel(Map<String, Object> model)
    {
      this.model = model;
    }

    public String getSubject()
    {
      return this.subject;
    }

    public void setSubject(String subject)
    {
      this.subject = subject;
    }

    public String getFrom()
    {
      return this.from;
    }

    public void setFrom(String from)
    {
      this.from = from;
    }

    public String[] getTo()
    {
      return this.to;
    }

    public void setTo(String[] to)
    {
      this.to = to;
    }

    public String getText()
    {
      return this.text;
    }

    public void setText(String text)
    {
      this.text = text;
    }

    public List<String> getFiles()
    {
      return this.files;
    }

    public void setFiles(List<String> files)
    {
      this.files = files;
    }

    public String getVmTemplate()
    {
      return this.vmTemplate;
    }

    public void setVmTemplate(String vmTemplate)
    {
      this.vmTemplate = vmTemplate;
    }

    public String[] getCc()
    {
      return this.cc;
    }

    public void setCc(String[] cc)
    {
      this.cc = cc;
    }

    public String[] getBcc()
    {
      return this.bcc;
    }

    public void setBcc(String[] bcc)
    {
      this.bcc = bcc;
    }

    public void setEncoding(String encoding)
    {
      this.encoding = encoding;
    }

    public String getFromTitle()
    {
      return this.fromTitle;
    }

    public void setFromTitle(String fromTitle)
    {
      this.fromTitle = fromTitle;
    }

    public Object clone()
    {
      Object o = null;
      try
      {
        o = super.clone();
      }
      catch (CloneNotSupportedException e)
      {
        e.printStackTrace();
      }
      return o;
    }
  }